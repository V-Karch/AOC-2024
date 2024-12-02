#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

int main() {
    ifstream file("input.txt");
    vector<string> lines;

    if (file.is_open()) {
        string line;
        while (getline(file, line)) {
            lines.push_back(line);
        }
        file.close();
    } else {
        cerr << "Could not open the file!" << endl;
        return 1;
    }

    for (const string &line : lines) {
        cout << line << endl;
    }

    return 0;
}
