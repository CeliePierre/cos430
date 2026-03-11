.data
# Space for 20 CartItems, each 24 bytes (20 for name, 4 for price)
CartItems: .space 480  

# New strings for CartItem input and prompts
NamePrompt: .asciiz "Enter the name of the item: "
PricePrompt: .asciiz "Enter the price of the item: "
ContinuePrompt: .asciiz "Is there another item to add to the cart? 1 for yes, 0 for no: "
UnsortedOutput: .asciiz "\nUnsorted Cart\n"
SortedOutput: .asciiz "\nSorted Cart\n"

# Original strings from your program
Prompt1: .asciiz "Enter a value between 1 and 20!\n"
Prompt2: .asciiz "How many elements will you enter? ==> "
EnterElement: .asciiz "Enter array element #"
Pointer: .asciiz " ==> "
FirstOutput: .asciiz "\nUnsorted Array\n"
Comma: .asciiz ", "
RightSq: .asciiz "["
LeftSq: .asciiz "]"
BackSpace: .asciiz "\n"
SecondOutput: .asciiz "\nSorted Array\n"


main:
    # Initial setup
    # Loop to input CartItems
    la $a0, CartItems         # Base address of CartItems array
    li $t1, 0                 # Counter for number of items

input_loop:
    bge $t1, 20, sort_items   # Go to sort if 20 items are entered
    bge $t1, 20, sort_items  # Check if the limit of 20 items is reached

    # Input name
    printString NamePrompt
    la $a0, CartItems      # Load address of CartItems into $a0
    li $a1, 20             # Maximum length for the name
    mul $t2, $t1, 24       # Calculate offset (24 bytes per CartItem)
    add $a0, $a0, $t2      # Adjust address for the current CartItem
    readString             # Read string into the correct position
    
    # Assuming you have just read and stored the name
    printString PricePrompt
    readInt                  # System call to read an integer
    move $t3, $v0            # Move the read value (price) into $t3

    # Calculate the address to store the price
    la $a0, CartItems        # Load address of CartItems into $a0
    li $t4, 24               # Size of each CartItem
    mul $t2, $t1, $t4        # Calculate offset for current CartItem
    addi $t2, $t2, 20        # Offset to reach the price field
    add $a0, $a0, $t2        # Calculate the address for the price
    sw $t3, 0($a0)           # Store the price at the calculated address

    # Update the counter and check if more items should be added
    addi $t1, $t1, 1         # Increment the item counter
  
     # After storing the price

    # Ask if the user wants to add another item
    printString ContinuePrompt
    readInt                  # System call to read an integer (user's decision)
    move $t4, $v0            # Move the decision into $t4

    # Check user's decision
    li $t5, 1                # Load the value 1 into $t5
    beq $t4, $t5, input_loop # If the user entered 1, loop back to add more items

    # If the user did not enter 1, proceed to sort the items
    j sort_items             # Jump to the sorting routine
    sort_items:
    # Assume $t1 contains the number of items in the array
    li $t2, 0               # Outer loop counter
    li $t3, 0               # Inner loop counter

outer_loop:
    bge $t2, $t1, end_sort  # Exit loop if outer loop counter reaches number of items
    li $t3, 0               # Reset inner loop counter for each iteration of outer loop

inner_loop:
    addi $t4, $t3, 1        # Set $t4 to the next index
    bge $t4, $t1, update_outer # Exit inner loop if next index reaches number of items

    # Calculate addresses of current and next item prices
    li $t5, 24              # Size of each CartItem
    mul $t6, $t3, $t5       # Offset for current item
    add $t6, $t6, 20        # Offset to reach the price field of current item
    add $t6, $t6, CartItems # Address of current item's price

    mul $t7, $t4, $t5       # Offset for next item
    add $t7, $t7, 20        # Offset to reach the price field of next item
    add $t7, $t7, CartItems # Address of next item's price

    # Load prices for comparison
    lw $t8, 0($t6)          # Load price of current item
    lw $t9, 0($t7)          # Load price of next item

    # Compare and potentially swap
    ble $t8, $t9, no_swap  # If current item price is less or equal, no swap needed
    # Call a function or implement logic to swap the two CartItems
    # Swap function needs to swap both name and price

no_swap:
    addi $t3, $t3, 1       # Increment inner loop counter
    j inner_loop

update_outer:
    addi $t2, $t2, 1       # Increment outer loop counter
    j outer_loop
    print_loop:
    bge $t2, $t1, end_print  # Check if all items have been printed

    # Calculate address of current CartItem
    li $t3, 24                # Size of each CartItem
    mul $t4, $t2, $t3         # Offset for current item
    add $a0, CartItems, $t4   # Address of current item

    # Print name
    li $v0, 4                 # syscall to print string
    syscall

    # Calculate address of price
    addi $a0, $a0, 20         # Move to price field of current item
    lw $a1, 0($a0)            # Load price

    # Print price
    move $a0, $a1             # Move price to $a0
    li $v0, 1                 # syscall to print integer
    syscall
    # After printing name and price of a CartItem

   # Print a newline
li $v0, 4                  # syscall to print string
la $a0, newline            # Load the address of the newline string
syscall