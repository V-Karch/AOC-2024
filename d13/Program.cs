using System;
using System.IO;
using System.Collections.Generic;

class Program {
    static void Main() {
        if (!File.Exists("input.txt")) {
            Console.WriteLine("File not found.");
            return;
        }

        List<string> lines = new List<string>(File.ReadAllLines("input.txt"));

        foreach (string line in lines) {
            Console.WriteLine(line);
        }
    }
}
