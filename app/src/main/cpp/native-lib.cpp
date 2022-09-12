#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_creditscore_creditscorecheck_loan_util_Constant_getMainApi(
        JNIEnv *env, jclass clazz) {

    std::string hello = "http://206.189.136.121/nv/CreditScoreNV427/V1/";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_creditscore_creditscorecheck_loan_encrypt_DecryptEncrypt_encryptionKey(
        JNIEnv *env, jclass clazz) {

    std::string hello = "";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_creditscore_creditscorecheck_loan_encrypt_DecryptEncrypt_zipencryptionkey(
        JNIEnv *env, jclass clazz) {

    std::string hello = "";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_creditscore_creditscorecheck_loan_encrypt_DecScript_getDecKey1(
        JNIEnv *env, jclass clazz) {

    std::string hello = "A61B0106A751B2C99E9EE110847524DC0CC8EE72A457C3AAA35F79627FD13728";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_creditscore_creditscorecheck_loan_encrypt_DecScript_getDecKey2(
        JNIEnv *env, jclass clazz) {

    std::string hello = "5E5CC6CF1E4E057371070CFBA2EC7A4B0CC8EE72A457C3AAA35F79627FD13728";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_creditscore_creditscorecheck_loan_encrypt_EncScript_getEncKey1(
        JNIEnv *env, jclass clazz) {

    std::string hello = "";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_creditscore_creditscorecheck_loan_encrypt_EncScript_getEncKey2(
        JNIEnv *env, jclass clazz) {

      std::string hello = "";
    return env->NewStringUTF(hello.c_str());
}