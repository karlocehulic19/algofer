#include "join_array.h"
#include <gtest/gtest.h>
#include <string>
#include <vector>

using std::string;
using std::vector;

namespace {
TEST(joinArray, ConvertBasicStringArray) {
  EXPECT_EQ(joinArray({"null", "null", "true", "1", "2", "null"}), "[null,null,true,1,2,null]");
}
} // namespace
