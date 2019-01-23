# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c99")

include_directories(${CMAKE_CURRENT_SOURCE_DIR}
        )

set(cpp_files
        ${CMAKE_CURRENT_SOURCE_DIR}/prof.c
        )

add_library(android-ndk-profiler
        STATIC
        ${cpp_files})

target_link_libraries(android-ndk-profiler
        )
