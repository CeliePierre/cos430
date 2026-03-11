###########################################################
#  joel kalala
#  Playing with : Macro
#  Simple input output of string and intergers
#  stopping execution
###########################################################
.data
num1_Q1: .word 0x19      # 11001
num2_Q1: .word 0x0B      # 01011

num1_Q2: .word 0x06      # 00110
num2_Q2: .word 0x15      # 10101

num1_Q3: .word 0x19      # 11001
num2_Q3: .word 0x0B      # 01011

num1_Q4: .word 0x12      # 10010
num2_Q4: .word 0x0B      # 01011 

num_Q5_Q6:   .word 0x0B   # 00001011
num_Q7_Q8:   .word 0xC9   # 11001001
  

message_Q1:  .asciiz "\nQ1: 11001 NOR 01011 = "
message_Q2:  .asciiz "\nQ2: 00110 NOR 10101 = "
message_Q3:  .asciiz "\nQ3: 11001 XOR 01011 = "
message_Q4:  .asciiz "\nQ4: 10010 XOR 01011 = "
message_Q5:  .asciiz "\nQ5: 00001011 shift left by 3 = "
message_Q6:  .asciiz "\nQ6: 00001011 shift left by 4 = "
message_Q7:  .asciiz "\nQ7: 11001001 shift right logical by 2 = "
message_Q8:  .asciiz "\nQ8: 11001001 shift right arithmetic by 3 = "

.text

# Q1: NOR Operation
lw $t0, num1_Q1
lw $t1, num2_Q1
nor $t2, $t0, $t1
li $v0, 4
la $a0, message_Q1
syscall
li $v0, 1
move $a0, $t2
syscall

# Q2: NOR Operation
lw $t0, num1_Q2
lw $t1, num2_Q2
nor $t3, $t0, $t1
li $v0, 4
la $a0, message_Q2
syscall
li $v0, 1
move $a0, $t3
syscall

# Q3: XOR Operation
lw $t0, num1_Q3
lw $t1, num2_Q3
xor $t4, $t0, $t1
li $v0, 4
la $a0, message_Q3
syscall
li $v0, 1
move $a0, $t4
syscall

# Q4: XOR Operation
lw $t0, num1_Q4
lw $t1, num2_Q4
xor $t5, $t0, $t1
li $v0, 4
la $a0, message_Q4
syscall
li $v0, 1
move $a0, $t5
syscall

# Q5: Shift Left Logical by 3 bits
lw $t0, num_Q5_Q6
sll $t6, $t0, 3
li $v0, 4
la $a0, message_Q5
syscall
li $v0, 1
move $a0, $t6
syscall

# Q6: Shift Left Logical by 4 bits
lw $t0, num_Q5_Q6
sll $t7, $t0, 4
li $v0, 4
la $a0, message_Q6
syscall
li $v0, 1
move $a0, $t7
syscall

# Q7: Shift Right Logical by 2 bits
lw $t0, num_Q7_Q8
srl $t8, $t0, 2
li $v0, 4
la $a0, message_Q7
syscall
li $v0, 1
move $a0, $t8
syscall

# Q8: Shift Right Arithmetic by 3 bits
lw $t0, num_Q7_Q8
sra $t9, $t0, 3
li $v0, 4
la $a0, message_Q8
syscall
li $v0, 1
move $a0, $t9
syscall

# End of program
li $v0, 10
syscall