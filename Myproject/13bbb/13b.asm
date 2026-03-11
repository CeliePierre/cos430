###########################################################
#  joel kalala
#  Playing with : Macro
# each one should be tested do the code one question at a time
# 2 vars 1 relation = 2 tests (T,F)
# I've started Q1 for you. Change value of s1 for second test
# Then move to the second question code
################################################################ 
.data 
newline: .asciiz "\n"

.text

# Check if $s0 > 0
blez   $s0, endIf  # If $s0 is less than or equal to 0, skip to end

# Nested If: Check if $s0 < $s1
blt    $s0, $s1, thenPart
b      elsePart

thenPart:
# If $s0 is less than $s1
addi   $s0, $s0, 1
b      endIf

elsePart:
# If $s0 is greater than or equal to $s1
addi   $s0, $s0, 2

endIf:

# Print the value of $s0
li      $v0, 1       # System call code for printing integer
move    $a0, $s0    # Move the value of $s0 to $a0 to print
syscall

# Print a newline
li      $v0, 4       # System call code for printing string
la      $a0, newline
syscall

# Exit the program
li      $v0, 10      # System call code for exiting program
syscall