#include <string>
#include <vector>

using namespace std;

vector<string> splitArray(string actionsString) {
  string trimmedActions = actionsString.substr(1, actionsString.length() - 2);
  int prevIndex = 0;
  int splitIndex = trimmedActions.find(",");
  int bracketStartIndex = trimmedActions.find("[");
  int bracketEndIndex = trimmedActions.find("]");
  vector<string> actions;

  while (splitIndex != string::npos) {
    while (bracketEndIndex != string::npos && splitIndex > bracketEndIndex) {
      bracketStartIndex = trimmedActions.find("[", bracketStartIndex + 1);
      bracketEndIndex = trimmedActions.find("]", bracketEndIndex + 1);
    };

    bool isDelimiterInsideBrackets =
        (bracketEndIndex != string::npos && splitIndex > bracketStartIndex &&
         splitIndex < bracketEndIndex);

    if (isDelimiterInsideBrackets) {
      splitIndex = trimmedActions.find(",", splitIndex + 1);
      continue;
    }

    actions.push_back(trimmedActions.substr(prevIndex, splitIndex - prevIndex));
    prevIndex = splitIndex + 1;
    splitIndex = trimmedActions.find(",", splitIndex + 1);
  }

  actions.push_back(trimmedActions.substr(prevIndex));

  return actions;
}
