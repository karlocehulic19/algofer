cmake_minimum_required(VERSION 3.14)
project(my_project)

# GoogleTest requires at least C++17
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

include(FetchContent)
FetchContent_Declare(
  googletest
  URL https://github.com/google/googletest/archive/03597a01ee50ed33e9dfd640b249b4be3799d395.zip
)

find_package(Boost REQUIRED)
# For Windows: Prevent overriding the parent project's compiler/linker settings
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(googletest)

enable_testing()

add_executable(
  split_array_test
  split_array_test.cc
  split_array.cc
)
target_link_libraries(
  split_array_test
  GTest::gtest_main
)

include(GoogleTest)
gtest_discover_tests(split_array_test)

add_executable(
  join_array_test
  join_array_test.cc
  join_array.cc
)
target_link_libraries(
  join_array_test
  GTest::gtest_main
)

include(GoogleTest)
gtest_discover_tests(join_array_test)
