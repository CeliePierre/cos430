###########################################################
#  joel kalala
#  Playing with : Macro
#  Simple input output of string and intergers
#  stopping execution
###########################################################
.data
# Part 1
value: .word 0xC1700000      # IEEE representation of -2000
sign_mask: .word 0x80000000  # Mask to isolate sign bit

# Part 2
pVal: .word 0x0000C4FA

# Part 3
exp_mask: .word 0x7F800000   # Mask to isolate exponent bits

# Strings for output
part1_str: .asciiz "Output for Part 1: "
part2_str: .asciiz "Output for Part 2: "
part3_str: .asciiz "Output for Part 3: "
newline: .asciiz "\n"

.text
main:

# Programming Exercise Part 1
    lw $s0, value           # Load value into $s0
    lw $s1, sign_mask       # Load sign mask into $s1
    and $s2, $s0, $s1       # Logical AND operation to get sign bit
    
    # Print Part 1
    li $v0, 4
    la $a0, part1_str
    syscall
    li $v0, 1
    move $a0, $s2
    syscall
    li $v0, 4
    la $a0, newline
    syscall

# Programming Exercise Part 2
    not $s1, $s1            # Invert the sign mask
    xor $s3, $s0, $s1       # Flip the sign bit
    
    # Print Part 2
    li $v0, 4
    la $a0, part2_str
    syscall
    li $v0, 1
    move $a0, $s3
    syscall
    li $v0, 4
    la $a0, newline
    syscall

# Programming Exercise Part 3
    lw $s1, exp_mask        # Load the exponent mask into $s1
    and $s4, $s0, $s1       # Isolate exponent bits

    srl $s4, $s4, 23        # Shift the exponent to the rightmost 8 bits
    addi $s4, $s4, -127     # Subtract the bias
    
    # Print Part 3
    li $v0, 4
    la $a0, part3_str
    syscall
    li $v0, 1
    move $a0, $s4
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Exit
    li $v0, 10
    syscall