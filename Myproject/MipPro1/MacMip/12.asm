###########################################################
#  joel kalala
#  Playing with :
#  Simple input output of string and intergers
#  stopping execution
###########################################################
# Macro to halt the program
.macro halt
li $v0, 10
syscall
.end_macro

# Macro to print a string given its label
.macro printStrAtLabel(%lbl)
li $v0, 4
la $a0, %lbl
syscall
.end_macro

# Macro to print the contents of a register
.macro printInt(%reg)
move $a0, %reg
li $v0, 1
syscall
.end_macro

# Macro to print a 32-bit integer located at a memory address
.macro printIntAt(%addr)
lw $a0, %addr
li $v0, 1
syscall
.end_macro

# Macro to read an integer from the keyboard and move it into a specified register
.macro readInt(%reg)
li $v0, 5
syscall
move %reg, $v0
.end_macro

################# END OF MACROS #####################

.data
anInt: .word 50
prompt: .asciiz "Please enter an integer --> "
menu: .asciiz "***************\n* 1. item 1 *\n* 2. item 2 *\n***************\n"
newline: .asciiz "\n"
selectStr: .asciiz "Select one of the following\n"
enteredStr: .asciiz "You entered: "

.text
main:
# Let's test our macros

printIntAt(anInt)           # This will print the integer 50
printStrAtLabel(newline)    # Print a newline

# Print the literal "Select one of the following"
printStrAtLabel(selectStr)

# Print the menu
printStrAtLabel(menu)

# Print the prompt
printStrAtLabel(prompt)

# Read an integer and store it in $t0
readInt($t0)

# Print the literal "You entered: "
printStrAtLabel(enteredStr)

# Print the entered integer
printInt($t0)

# Print a newline for clean output
printStrAtLabel(newline)

# Halt the program
halt