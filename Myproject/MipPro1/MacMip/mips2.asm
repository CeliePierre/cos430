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

.text

main:
    lw $t0, num1_Q1      # Load numbers to registers
    lw $t1, num2_Q1
    nor $t2, $t0, $t1    # NOR operation Q1
    # Result of Q1 is in $t2

    lw $t0, num1_Q2
    lw $t1, num2_Q2
    nor $t3, $t0, $t1    # NOR operation Q2
    # Result of Q2 is in $t3

    lw $t0, num1_Q3
    lw $t1, num2_Q3
    xor $t4, $t0, $t1    # XOR operation Q3
    # Result of Q3 is in $t4

    lw $t0, num1_Q4
    lw $t1, num2_Q4
    xor $t5, $t0, $t1    # XOR operation Q4
    # Result of Q4 is in $t5

    # ... (You can print results or store them to memory as per your requirement)

    # Exit the program
    li   $v0, 10
    syscall