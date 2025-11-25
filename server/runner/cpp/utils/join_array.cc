#include <string>
#include <vector>
#include <boost/algorithm/string/join.hpp>

using std::string;
using std::vector;

string joinArray(vector<string> arrayToJoin) {
  string joinedString = boost::algorithm::join(arrayToJoin, ",");

  return "[" + joinedString + "]";
}
