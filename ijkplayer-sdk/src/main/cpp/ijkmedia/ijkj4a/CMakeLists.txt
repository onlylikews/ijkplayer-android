# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(j4a_base_dir ${CMAKE_CURRENT_SOURCE_DIR})
set(j4a_src_dir ${j4a_base_dir}/j4a)
set(j4au_src_dir ${j4a_base_dir}/j4au)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c99")

set(j4a_src_files
        ${j4a_src_dir}/j4a_allclasses.c
        ${j4a_src_dir}/j4a_base.c
        ${j4a_src_dir}/class/android/media/AudioTrack.c
        ${j4a_src_dir}/class/android/media/MediaCodec.c
        ${j4a_src_dir}/class/android/media/MediaFormat.c
        ${j4a_src_dir}/class/android/media/PlaybackParams.c
        ${j4a_src_dir}/class/android/os/Build.c
        ${j4a_src_dir}/class/android/os/Bundle.c
        ${j4a_src_dir}/class/java/nio/Buffer.c
        ${j4a_src_dir}/class/java/nio/ByteBuffer.c
        ${j4a_src_dir}/class/java/util/ArrayList.c
        ${j4a_src_dir}/class/tv/danmaku/ijk/media/player/misc/IMediaDataSource.c
        ${j4a_src_dir}/class/tv/danmaku/ijk/media/player/misc/IAndroidIO.c
        ${j4a_src_dir}/class/tv/danmaku/ijk/media/player/IjkMediaPlayer.c

        ${j4au_src_dir}/class/android/media/AudioTrack.util.c
        ${j4au_src_dir}/class/java/nio/ByteBuffer.util.c
        )

include_directories(${ANDROID_NDK}/sources/android/cpufeatures
        ${CMAKE_CURRENT_SOURCE_DIR})

add_library(ijkj4a
        STATIC
        ${j4a_src_files})