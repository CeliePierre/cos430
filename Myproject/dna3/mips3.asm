.data
# Space for 20 CartItems, each 24 bytes (20 for name, 4 for price)
CartItems: .space 480  

# Strings for CartItem input and prompts
NamePrompt: .asciiz "Enter the name of the item: "
PricePrompt: .asciiz "Enter the price of the item: "
ContinuePrompt: .asciiz "Is there another item to add to the cart? 1 for yes, 0 for no: "
UnsortedOutput: .asciiz "\nUnsorted Cart\n"
SortedOutput: .asciiz "\nSorted Cart\n"
newline: .asciiz "\n"

.text
.globl main
main:
    # Initial setup
    la $a0, CartItems         # Base address of CartItems array
    li $t1, 0                 # Counter for number of items

input_loop:
    # Check if limit of 20 items is reached
    bge $t1, 20, sort_items  

    # Input name
    #printString NamePrompt
    # Calculate address for the name
    la $a0, CartItems        
    li $a1, 20               
    mul $t2, $t1, 24         
    add $a0, $a0, $t2        
    #readString               

    # Input price
    #printString PricePrompt
    #readInt                  
    move $t3, $v0            

    # Calculate address for the price
    la $a0, CartItems        
    li $t4, 24               
    mul $t2, $t1, $t4        
    addi $t2, $t2, 20        
    add $a0, $a0, $t2        
    sw $t3, 0($a0)           

    # Update the counter
    addi $t1, $t1, 1         

    # Ask if the user wants to add another item
    #printString ContinuePrompt
    #readInt                  
    move $t4, $v0            
    li $t5, 1                
    beq $t4, $t5, input_loop 

    j sort_items             

sort_items:
    # Sorting logic (not detailed here)
    # ...
    #sort_items:
    li $t2, 0                # Counter for passes
    li $t3, 1                # A flag to check if a swap occurred

start_pass:
    li $t3, 0                # Reset swap flag for this pass
    li $t4, 0                # Inner loop index

inner_loop:
    bge $t4, $t1, check_swap # End of one pass through the array
    mul $t5, $t4, 24         # Calculate the offset of the current item
   la $t0, CartItems  # Load address of CartItems into $t0
    lw $t6, 20($t5)          # Load the current item's price

    addi $t7, $t4, 1         # Next item index
    mul $t8, $t7, 24         # Calculate the offset of the next item
lw $t1, 0($t0)     # Load the word at the start of the array into $t1

    lw $t9, 20($t8)          # Load the next item's price

    # Compare and potentially swap
    ble $t6, $t9, no_swap    # If current <= next, no swap needed
    # Swap logic here (swap entire CartItems)
    li $t3, 1                # Set swap flag

no_swap:
    addi $t4, $t4, 1         # Increment inner loop index
    j inner_loop             # Repeat inner loop

check_swap:
    beqz $t3, end_sort       # If no swap occurred, array is sorted
    addi $t2, $t2, 1         # Increment pass counter
    j start_pass             # Start the next pass
    
    

end_sort:
    # Sorting is complete at this point
    # Now print the sorted CartItems
    #printString SortedOutput  # Print a header for sorted CartItems
    li $t2, 0                 # Reset the index for printing

print_loop:
    bge $t2, $t1, end_program  # Check if all items have been printed

    # Calculate address of the current CartItem
    li $t3, 24                  # Size of each CartItem
    mul $t4, $t2, $t3           # Offset for the current CartItem
   la $t0, CartItems  # Load address of CartItems into $t0

    # Print the name of the CartItem
    li $v0, 4                   # syscall to print string
    syscall

    # Calculate the address of the CartItem's price and print it
    addi $a0, $a0, 20           # Address of the price
    lw $a1, 0($a0)              # Load the price
    move $a0, $a1               # Move the price to $a0
    li $v0, 1                   # syscall to print integer
    syscall

    # Print a newline after each CartItem
    li $v0, 4
    la $a0, newline
    syscall

    addi $t2, $t2, 1            # Increment the index
    j print_loop                # Go to the next CartItem

end_program:
    # Exit the program
    li $v0, 10                  # Load the exit syscall code
    syscall