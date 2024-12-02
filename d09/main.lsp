(defun read-lines-into-array (filename) "Reads the lines of the file into a vector, preserving the order." 
  (with-open-file (stream filename) 
    (let ((lines '())) 
    (loop for line = 
      (read-line stream nil) while line do 
      (setq lines 
        (append lines (list line)))) ; Append to maintain order
 
    (coerce lines 'vector)))) 

(defun print-vector (vector) "Prints the contents of the vector." 
  (loop for element across vector do 
    (format t "~A~%" element))) 

(defun main () "Main entry point for the program." 
  (let 
    (
      (lines-array 
        (read-lines-into-array "input.txt"))) 
    (print-vector lines-array)))