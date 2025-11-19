#include "split_array.h"
#include <gtest/gtest.h>
#include <string>
#include <vector>

using std::string;
using std::vector;

namespace {
TEST(splitArray, SplitsBasicStringArrays) {
  vector<string> basic = {"A", "B", "C"};
  EXPECT_EQ(splitArray("[A,B,C]"), basic);
}
} // namespace
