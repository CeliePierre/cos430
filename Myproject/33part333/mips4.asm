###########################################################
#  Author: Joel Kalala
#  Purpose: Experimentation with MIPS Assembly Language
#  Project: #3 - Working with Arrays
#  Description: This code demonstrates the use of arrays
#               in MIPS assembly by prompting the user to
#               input a set of values, which are then sorted
#               using the bubble sort algorithm.
##########################################################
.data
    array:          .space 80         # Reserve space for 20 integers (4 bytes each)
    sizePrompt:     .asciiz "How many elements will you enter? ==> "
    elemPromptPart1: .asciiz "Enter array element #"
    elemPromptPart2: .asciiz " ==> "
    unsortedMsg:    .asciiz "Unsorted Array\n"
    sortedMsg:      .asciiz "Sorted Array\n"
    newline:        .asciiz "\n"
    commaSpace:     .asciiz ", "
    space:          .asciiz " "
    leftBracket:    .asciiz "["
    rightBracket:   .asciiz "]\n"
    numElements:    .word 0           # A place to store the number of elements

.text
.globl main
main:
    # Print size prompt
    li $v0, 4
    la $a0, sizePrompt
    syscall

    # Read number of elements
    li $v0, 5
    syscall
    sw $v0, numElements
    lw $t0, numElements             # Load the number of elements into $t0

    # Check if the number of elements is between 1 and 20
    li $t1, 1                       # Lower bound
    li $t2, 20                      # Upper bound
    blt $v0, $t1, main              # If less than 1, prompt again
    bgt $v0, $t2, main              # If greater than 20, prompt again

    # Now read each element into the array
    li $t3, 0                       # Index for the array
read_loop:
    blt $t3, $t0, read_element      # If we've not reached the required number, read next element
    j print_unsorted                # Else, we've read all elements, proceed to print them

read_element:
    # Print static part of the prompt "Enter array element #"
    li $v0, 4
    la $a0, elemPromptPart1
    syscall

    # Print the current element number (human-readable, starting from 1)
    li $v0, 1
    addi $a0, $t3, 1  # Element count starts from 1
    syscall

    # Print static part of the prompt " ==> "
    li $v0, 4
    la $a0, elemPromptPart2
    syscall

    # Read element from the user, input will follow immediately after " ==> "
    li $v0, 5
    syscall

    # Store element in the array at the correct address
    sll $t4, $t3, 2                 # Index multiplied by 4 to get address offset
    la $t5, array                   # Base address of array
    add $t5, $t5, $t4               # Address of the current element
    sw $v0, 0($t5)                  # Store the input value in the array

    # Increment index for the next iteration
    addi $t3, $t3, 1
    j read_loop                     # Jump back to the loop condition

print_unsorted:
    # Print the unsorted array
    li $v0, 4
    la $a0, unsortedMsg
    syscall
    lw $a0, numElements
    jal print_array

    # Perform bubble sort on the array
    jal bubble_sort

print_sorted:
    # Print the sorted array
    li $v0, 4
    la $a0, sortedMsg
    syscall
    lw $a0, numElements
    jal print_array

    # Exit the program
    li $v0, 10
    syscall

print_array: # $a0 - Number of elements to print
    li $t3, 0                        # Index for printing elements
    li $v0, 4
    la $a0, leftBracket
    syscall
print_loop:
    # Calculate address of the current element to print
    sll $t4, $t3, 2                  # Index * 4
    la $t5, array
    add $t5, $t5, $t4                # Address of current element
    lw $t6, 0($t5)                   # Load current element

    # Print current element
    li $v0, 1
    move $a0, $t6
    syscall

    addi $t3, $t3, 1                 # Increment index
    blt $t3, $a0, print_comma_space  # If not done, print comma and space
    j print_bracket

print_comma_space:
    li $v0, 4
    la $a0, commaSpace
    syscall
    j print_loop

print_bracket:
    li $v0, 4
    la $a0, rightBracket
    syscall
    jr $ra

bubble_sort:  # Sort the array using bubble sort
    lw $t0, numElements              # Load the number of elements into $t0

outer_loop:
    li $t1, 0                        # Outer loop index, starts from 0
    move $t2, $t0                    # Copy numElements to $t2

outer_loop_start:
    beqz $t2, end_bubble_sort        # If $t2 is 0, all passes are done
    move $t3, $t1                    # Reset inner loop index

inner_loop:
    sll $t4, $t3, 2                  # Calculate current element's address offset
    la $t5, array                    # Load base address of array
    add $t5, $t5, $t4                # Get current element's address
    lw $t6, 0($t5)                   # Load current element
    lw $t7, 4($t5)                   # Load next element

    # Compare and possibly swap elements
    blez $t7, skip_swap              # If at the end of array, skip swap
    bgt $t6, $t7, swap_elements      # If elements are out of order, swap them

skip_swap:
    addi $t3, $t3, 1                 # Increment inner loop index
    blt $t3, $t2, inner_loop         # If not at the end of inner loop, branch

    addi $t1, $t1, 1                 # Increment outer loop index
    subi $t2, $t2, 1                 # Decrement loop counter for remaining passes
    j outer_loop_start               # Repeat outer loop

swap_elements:
    sw $t6, 4($t5)                   # Swap elements
    sw $t7, 0($t5)
    j skip_swap

end_bubble_sort:
    jr $ra                           # Return from bubble_sort