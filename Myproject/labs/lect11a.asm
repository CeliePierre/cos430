###########################################################
#  joel kalala
#   Playing with :
#     Simple input output of string and intergers
#     stopping execution
###########################################################
         .data 
 hello:  .asciiz        "hello world\n"
 
         .text 
main:     la         $a0,      hello #load adresss of the label into $ao
           li         $v0,       4    # code to print a string 
           syscall