###########################################################
#  joel kalala
#   Playing with :
#     Simple input output of string and intergers
#     stopping execution
###########################################################
         .data
hello: .asciiz "hello world\n"
.text
main: la $a0, hello # load address of the label into $a0
li $v0, 4 # code to print a String
syscall # system call to perform function in $v0
li $a0, 75 # load the integer into $a0
li $v0, 1 # load the code to print an integer
syscall # system call to perform function in $v0
li $v0, 10 # load the code to stop execution
syscall # system call to perform function in