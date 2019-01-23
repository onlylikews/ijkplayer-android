# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c99")

include_directories(${CMAKE_CURRENT_SOURCE_DIR}
        ${CMAKE_CURRENT_SOURCE_DIR}/..
        ${CMAKE_CURRENT_SOURCE_DIR}/include/
        )

set(cpp_files
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/AAFilter.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/AAFilter.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/FIFOSampleBuffer.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/FIRFilter.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/cpu_detect_x86.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/sse_optimized.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/RateTransposer.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/InterpolateCubic.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/InterpolateLinear.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/InterpolateShannon.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/TDStretch.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/BPMDetect.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/PeakFinder.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/SoundTouch.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/source/SoundTouch/mmx_optimized.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/ijksoundtouch_wrap.cpp
        )

include_directories(${ANDROID_NDK}/sources/android/cpufeatures
        ${CMAKE_CURRENT_SOURCE_DIR})

add_library(ijksoundtouch
        STATIC
        ${cpp_files})

target_link_libraries(ijksoundtouch
        log
        android
        )
