###########################################################
#  joel kalala
#   Playing with :Macro
#    project 2 : Shipping  
###########################################################
   .data
welcome_msg:    .asciiz "Welcome to Joelkalala Shipping\n"
vol_prompt:     .asciiz "Please enter package volume in cubic inches between 1 and 8000: "
weight_prompt:  .asciiz "Please enter package weight in pounds between 1 and 100: "
menu:           .asciiz "1) Ground 5-10 business days\n2) Super saver air 2-4 business days\n3) Next day air\nPlease select your shipping speed. Enter 1, 2, or 3: "
invoice_hdr:    .asciiz "Shipping Invoice\n"
invoice_vol:    .asciiz "Package volume in cubic inches: "
invoice_weight: .asciiz "\nPackage weight in pounds: "
invoice_speed:  .asciiz "\nShip via:  "
invoice_cost:   .asciiz "\nCost $"
repeat_prompt:  .asciiz "\nDo you want to ship another package? Enter 1 for Yes or 0 for No: "
goodbye_msg:    .asciiz "Goodbye.\n"
ground_msg:     .asciiz "Ground"
super_saver_msg:.asciiz "Super Saver Air"
next_day_msg:   .asciiz "Next Day Air"

.text
.globl main
main:
    la $a0, welcome_msg       # Display welcome message
    li $v0, 4
    syscall

    # Get package volume
get_volume:
    la $a0, vol_prompt
    li $v0, 4
    syscall
    li $v0, 5
    syscall
    blt $v0, 1, get_volume
    bgt $v0, 8000, get_volume
    move $s0, $v0

    # Get package weight
get_weight:
    la $a0, weight_prompt
    li $v0, 4
    syscall
    li $v0, 5
    syscall
    blt $v0, 1, get_weight
    bgt $v0, 100, get_weight
    move $s1, $v0

    # Get shipping method
get_shipping:
    la $a0, menu
    li $v0, 4
    syscall
    li $v0, 5
    syscall
    blt $v0, 1, get_shipping
    bgt $v0, 3, get_shipping
    move $s2, $v0

    # Calculate cost
calculate_cost:
    li $t0, 8                 # Base cost for Ground <= 1000 volume and <= 60 weight
    li $t1, 12                # Adjusted cost for Ground > 1000 volume or > 60 weight
    li $t2, 15                # Cost for Super Saver Air
    li $t3, 25                # Cost for Next Day Air

    # Depending on shipping method, select cost
    beq $s2, 1, select_ground
    beq $s2, 2, select_super_saver
    beq $s2, 3, select_next_day

select_ground:
    ble $s0, 1000, check_weight
    move $s3, $t1
    j display_invoice

check_weight:
    ble $s1, 60, set_ground_base
    move $s3, $t1
    j display_invoice

set_ground_base:
    move $s3, $t0
    j display_invoice

select_super_saver:
    move $s3, $t2
    j display_invoice

select_next_day:
    move $s3, $t3

display_invoice:
    la $a0, invoice_hdr
    li $v0, 4
    syscall

    # Display volume
    la $a0, invoice_vol
    li $v0, 4
    syscall
    move $a0, $s0
    li $v0, 1
    syscall

    # Display weight
    la $a0, invoice_weight
    li $v0, 4
    syscall
    move $a0, $s1
    li $v0, 1
    syscall

    # Display shipping method
    la $a0, invoice_speed
    li $v0, 4
    syscall
    beq $s2, 1, print_ground
    beq $s2, 2, print_super_saver
    
     # Display shipping method
    la $a0, invoice_speed
    li $v0, 4
    syscall
    beq $s2, 1, print_ground
    beq $s2, 2, print_super_saver
    j print_next_day

print_ground:
    la $a0, ground_msg
    li $v0, 4
    syscall
    j print_cost

print_super_saver:
    la $a0, super_saver_msg
    li $v0, 4
    syscall
    j print_cost

print_next_day:
    la $a0, next_day_msg
    li $v0, 4
    syscall

print_cost:
    # Display cost
    la $a0, invoice_cost
    li $v0, 4
    syscall
    move $a0, $s3
    li $v0, 1
    syscall

    # Ask the user if they want to ship another package
    la $a0, repeat_prompt
    li $v0, 4
    syscall
    li $v0, 5
    syscall

    # Check user's choice
    beq $v0, 1, main
    j exit_program

exit_program:
    la $a0, goodbye_msg
    li $v0, 4
    syscall
    li $v0, 10
    syscall