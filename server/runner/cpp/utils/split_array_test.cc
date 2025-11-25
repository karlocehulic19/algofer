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
TEST(splitArray, SplitsNestedStringArrays) {
  vector<string> nested = {"A", "[B,C,D]", "C"};
  EXPECT_EQ(splitArray("[A,[B,C,D],C]"), nested);
}
} // namespace
