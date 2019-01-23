# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c99")

include_directories(${CMAKE_CURRENT_SOURCE_DIR}
        ${CMAKE_CURRENT_SOURCE_DIR}/..
        ${ijkffmpeg_target_inc_dir}
        ${CMAKE_CURRENT_SOURCE_DIR}/../ijkyuv/include
        ${CMAKE_CURRENT_SOURCE_DIR}/../ijkj4a)

set(sdl_files
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_aout.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_audio.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_egl.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_error.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_mutex.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_stdinc.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_thread.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_timer.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksdl_vout.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/color.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/common.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/renderer.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/renderer_rgb.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/renderer_yuv420p.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/renderer_yuv444p10le.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/shader.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/fsh/rgb.fsh.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/fsh/yuv420p.fsh.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/fsh/yuv444p10le.fsh.c
        ${CMAKE_CURRENT_SOURCE_DIR}/gles2/vsh/mvp.vsh.c

        ${CMAKE_CURRENT_SOURCE_DIR}/dummy/ijksdl_vout_dummy.c

        ${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/ijksdl_vout_overlay_ffmpeg.c
        ${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/abi_all/image_convert.c

        ${CMAKE_CURRENT_SOURCE_DIR}/android/android_audiotrack.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/android_nativewindow.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_android_jni.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_aout_android_audiotrack.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_aout_android_opensles.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_codec_android_mediacodec_dummy.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_codec_android_mediacodec_internal.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_codec_android_mediacodec_java.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_codec_android_mediacodec.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_codec_android_mediadef.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_codec_android_mediaformat_java.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_codec_android_mediaformat.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_vout_android_nativewindow.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_vout_android_surface.c
        ${CMAKE_CURRENT_SOURCE_DIR}/android/ijksdl_vout_overlay_android_mediacodec.c
        )

add_library(ffmpeg
        SHARED
        IMPORTED)

set_target_properties(ffmpeg PROPERTIES IMPORTED_LOCATION
        ${ijkffmpeg_target_lib_path}/libijkffmpeg.so)

add_library(ijksdl
        SHARED
        ${sdl_files})

add_custom_command(TARGET ijksdl
        PRE_BUILD
        COMMAND ${CMAKE_CURRENT_SOURCE_DIR}/../ijkplayer/version.sh ${CMAKE_CURRENT_SOURCE_DIR}/../ijkplayer/ ijkversion.h
        COMMENT "This command will be executed before building target Test1")

target_link_libraries(ijksdl
        log
        android
        OpenSLES
        EGL
        GLESv2
        ijkj4a
        yuv_static
        ffmpeg
        )
