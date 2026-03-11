###########################################################
#  joel kalala
#   Playing with :
#     Simple input output of string and intergers
#     stopping execution
###########################################################
.data
num1_Q1: .word 25       # 11001 in binary
num2_Q1: .word 11       # 01011 in binary
num1_Q2: .word 6        # 00110 in binary
num2_Q2: .word 21       # 10101 in binary
num1_Q3: .word 25       # 11001 in binary
num2_Q3: .word 11       # 01011 in binary
num1_Q4: .word 18       # 10010 in binary
num2_Q4: .word 11       # 01011 in binary
num_Q5_Q6: .word 11     # 00001011 in binary
num_Q7_Q8: .word 201     # 11001001 in binary

.text

# Q1: NOR Operation
lw $t0, num1_Q1
lw $t1, num2_Q1
nor $t2, $t0, $t1
# Result of Q1 is in $t2

# Q2: NOR Operation
lw $t0, num1_Q2
lw $t1, num2_Q2
nor $t3, $t0, $t1
# Result of Q2 is in $t3

# Q3: XOR Operation
lw $t0, num1_Q3
lw $t1, num2_Q3
xor $t4, $t0, $t1
# Result of Q3 is in $t4

# Q4: XOR Operation
lw $t0, num1_Q4
lw $t1, num2_Q4
xor $t5, $t0, $t1
# Result of Q4 is in $t5

# Q5: Shift Left Logical by 3 bits
lw $t0, num_Q5_Q6
sll $t6, $t0, 3
# Result of Q5 is in $t6

# Q6: Shift Left Logical by 4 bits
lw $t0, num_Q5_Q6
sll $t7, $t0, 4
# Result of Q6 is in $t7

# Q7: Shift Right Logical by 2 bits
lw $t0, num_Q7_Q8
srl $t8, $t0, 2
# Result of Q7 is in $t8

# Q8: Shift Right Arithmetic by 3 bits
lw $t0, num_Q7_Q8
sra $t9, $t0, 3
# Result of Q8 is in $t9

# End of program
li $v0, 10
syscall