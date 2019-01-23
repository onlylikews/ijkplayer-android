# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

if(${ANDROID_ABI} STREQUAL armeabi-v7a)
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -mfloat-abi=soft -std=c99")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
elseif()
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c99")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
endif()

include_directories(${CMAKE_CURRENT_SOURCE_DIR}
        ${CMAKE_CURRENT_SOURCE_DIR}/..
        ${ijkffmpeg_target_inc_dir}
        ${CMAKE_CURRENT_SOURCE_DIR}/../ijkj4a
        )

set(ply_files
        ${CMAKE_CURRENT_SOURCE_DIR}/ff_cmdutils.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ff_ffplay.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ff_ffpipeline.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ff_ffpipenode.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkmeta.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkplayer.c

        ${CMAKE_CURRENT_SOURCE_DIR}/pipeline/ffpipeline_ffplay.c
        ${CMAKE_CURRENT_SOURCE_DIR}/pipeline/ffpipenode_ffplay_vdec.c

        ${CMAKE_CURRENT_SOURCE_DIR}/android/ffmpeg_api_jni.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijkplayer_android.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijkplayer_jni.c

        ${CMAKE_CURRENT_SOURCE_DIR}/android/pipeline/ffpipeline_android.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/pipeline/ffpipenode_android_mediacodec_vdec.c

        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/allformats.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijklivehook.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkmediadatasource.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkio.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkiomanager.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkiocache.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkioffio.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkioandroidio.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkioprotocol.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkioapplication.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkiourlhook.c

        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkasync.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijkurlhook.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijklongurl.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavformat/ijksegment.c

        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavutil/ijkdict.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavutil/ijkutils.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavutil/ijkthreadpool.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavutil/ijktree.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavutil/ijkfifo.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijkavutil/ijkstl.cpp
        )

add_library(ffmpeg
        SHARED
        IMPORTED)

set_target_properties(ffmpeg PROPERTIES IMPORTED_LOCATION
        ${ijkffmpeg_target_lib_path}/libijkffmpeg.so)

add_library(ijkplayer
        SHARED
        ${ply_files})

target_link_libraries(ijkplayer
        log
        android
        android-ndk-profiler
        ijksoundtouch
        ijksdl
        ffmpeg
        )
