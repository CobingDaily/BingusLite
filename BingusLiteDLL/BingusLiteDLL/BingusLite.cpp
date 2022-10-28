#include "pch.h"
#include "BingusLite.h"

BingusLite::BingusLite(HMODULE hModule)
{
	if (!attach())
	{
		FreeLibraryAndExitThread(hModule, 0);
		return;
	}

	if (!load(hModule))
	{
		jvmti->DisposeEnvironment();
		vm->DetachCurrentThread();
		FreeLibraryAndExitThread(hModule, 0);
		return;
	}

	Utils::messageBox("Injected!");

	jvmti->DisposeEnvironment();
	vm->DetachCurrentThread();
	FreeLibraryAndExitThread(hModule, 0);
}

bool BingusLite::attach()
{
	HMODULE jvmDLL = GetModuleHandle(L"jvm.dll");
	if (!jvmDLL)
	{
		Utils::messageBox("Failed to get jvm.dll");
		return false;
	}

	FARPROC jni_GetCreatedJavaVMsProcAddress = GetProcAddress(jvmDLL, "JNI_GetCreatedJavaVMs");
	if (!jni_GetCreatedJavaVMsProcAddress)
	{
		Utils::messageBox("Failed to get JNI_GetCreatedJavaVMs function");
		return false;
	}

	GetCreatedJavaVMs jni_GetCreatedJavaVMs = reinterpret_cast<GetCreatedJavaVMs>(jni_GetCreatedJavaVMsProcAddress);

	jsize count;
	if (jni_GetCreatedJavaVMs(&vm, 1, &count) != JNI_OK)
	{
		Utils::messageBox("Failed to get created JVMs");
		return false;
	}

	if (count == 0)
	{
		Utils::messageBox("No JVM found");
		return false;
	}

	if (vm->AttachCurrentThread(reinterpret_cast<void**>(&jni), nullptr) != JNI_OK)
	{
		Utils::messageBox("Failed to attach current thread to the JVM");
		return false;
	}

	if (vm->GetEnv(reinterpret_cast<void**>(&jvmti), JVMTI_VERSION) != JNI_OK)
	{
		Utils::messageBox("Failed to get JVMTI");
		vm->DetachCurrentThread();
		return false;
	}

	return true;
}

bool BingusLite::load(HMODULE hModule)
{
	jclass urlClassLoaderClass = jni->FindClass("java/net/URLClassLoader");
	jmethodID addURLMethodID = jni->GetMethodID(urlClassLoaderClass, "addURL", "(Ljava/net/URL;)V");

	jclass fileClass = jni->FindClass("java/io/File");
	jmethodID fileConstructorMethodID = jni->GetMethodID(fileClass, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V");
	jmethodID toURIMethodID = jni->GetMethodID(fileClass, "toURI", "()Ljava/net/URI;");
	
	jclass uriClass = jni->FindClass("java/net/URI");
	jmethodID toURLMethodID = jni->GetMethodID(uriClass, "toURL", "()Ljava/net/URL;");

	jobject classLoader = Utils::getThreadGroupClassLoader(jni);

	if (!jni->IsInstanceOf(classLoader, urlClassLoaderClass))
	{
		Utils::messageBox("Class Loader is not instanceof URLClassLoader");
		return false;
	}

	char path[MAX_PATH];

	GetModuleFileNameA(hModule, path, MAX_PATH);

	char* lastBackslash = strrchr(path, '\\');
	*lastBackslash = '\0';

	jstring jpath = jni->NewStringUTF(path);

	jobject bingusLiteJARFile = jni->NewObject(fileClass, fileConstructorMethodID, jpath, jni->NewStringUTF("BingusLiteJAR.jar"));
	jobject uri = jni->CallObjectMethod(bingusLiteJARFile, toURIMethodID);
	jobject url = jni->CallObjectMethod(uri, toURLMethodID);

	if (jni->ExceptionCheck())
	{
		jni->ExceptionDescribe();
		jni->ExceptionClear();
		return false;
	}

	jni->CallVoidMethod(classLoader, addURLMethodID, url);

	jclass classLoaderClass = jni->FindClass("java/lang/ClassLoader");
	jmethodID loadClassMethodID = jni->GetMethodID(classLoaderClass, "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;");

	jclass bingusLiteClass = reinterpret_cast<jclass>(jni->CallObjectMethod(classLoader, loadClassMethodID, jni->NewStringUTF("com.laz.binguslite.BingusLite")));
	jmethodID bingusLiteConstructorMethodID = jni->GetMethodID(bingusLiteClass, "<init>", "()V");
	jmethodID bingusLiteInitializeMethodID = jni->GetMethodID(bingusLiteClass, "initialize", "(Lcom/laz/binguslite/Client;Ljava/lang/ClassLoader;Ljava/lang/String;)Z");

	jclass clientClass = reinterpret_cast<jclass>(jni->CallObjectMethod(classLoader, loadClassMethodID, jni->NewStringUTF("com.laz.binguslite.Client")));
	jfieldID vanillaFieldID = jni->GetStaticFieldID(clientClass, "VANILLA", "Lcom/laz/binguslite/Client;");
	jfieldID lunarFieldID = jni->GetStaticFieldID(clientClass, "LUNAR", "Lcom/laz/binguslite/Client;");

	jobject client = nullptr;

	if (jni->FindClass("ave"))
	{
		client = jni->GetStaticObjectField(clientClass, vanillaFieldID);
	}
	else if (jni->FindClass("com/moonsworth/lunar/genesis/Genesis"))
	{
		client = jni->GetStaticObjectField(clientClass, lunarFieldID);
	}

	jobject bingusLite = jni->NewObject(bingusLiteClass, bingusLiteConstructorMethodID);

	if (!jni->CallBooleanMethod(bingusLite, bingusLiteInitializeMethodID, client, classLoader, jpath))
	{
		return false;
	}

	return true;
}