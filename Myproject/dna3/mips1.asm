.data
# Declaring an array that can contain up to 20 elements.
myArray: .space 80        # Reserve 80 bytes 

# Strings for output
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

.text
# Macros
# This is a macro that ends the program
.macro done
    li $v0, 10  # Load the code for the 'exit' system call into $v0
    syscall     # Perform the system call
.end_macro

# Print string macro
.macro printString(%String)
    li $v0, 4            # Set the $v0 register to 4, which is the code 
#for printing a string
    la $a0, %String      # Load the address of the string to be 
    #printed into $a0 
    syscall              # Call the system call to print the string
.end_macro

# Read string macro
.macro readString
    li $v0, 8   # value of register $v0 to 8 and then calling 
                #the system call instruction to perform the read. 
    syscall
.end_macro

# Print integer macro
.macro printInt(%integer)
    li $v0, 1
    move $a0, %integer
    syscall
.end_macro

# Read integer macro
.macro readInt
    li $v0, 5
    syscall
.end_macro

# Main Program Starts Here
main:
    la $a0, myArray            # Load address of myArray into $a0
    printString Prompt1       # Print Prompt1
    printString Prompt2       # Print Prompt2
    readInt                  # Read integer from user
    move $t1, $v0             # Move read value to $t1
    li,	$t3,	0
    blez $t1, getNumEle        # If number is <= 0, get number again
    bgt $t1, 20, getNumEle     # If number is > 20, get number again
    getNumEle:
	
    la $a0, myArray		#Load the address of myArray into $a0
    move    $t1,    $v0         # save the value received from the user  in  register  $t1	
    ble $t1, 0, getNumEle	#If the number entered is less than or equal to 0, then prompt for input again.
    bgt	$t1, 20, getNumEle	#If the number entered is greater than 20, then prompt for input again.
	
while:
	  
	  beq $t0, $t1, printFistOutPut       #if $t0 becomes equal to  the number  entered by the user 
	   			    	      #then go to the exit.		    
	  beq $t7, $t1, printFistOutPut       #if $t7 becomes equal to the number  entered by the user 
	   			    	      #then go to the exit.
	   			    	      		    		    
	   addi $t7, $t7, 1  #Increment $t0 by 1
	   
	   printString(EnterElement)  # Print the which the number of the next element to input.
	   printInt($t7)		
	   printString(Pointer)	      #Show where input need to be typed.
	   readInt             	     #receive the element from the user
           move    $s0,    $v0         # save the value received from the user  in  register  $s0
           
           sw $s0, myArray($t3)		#Now take the above input, and store in the next available index in the array
           
           addi $t3, $t3, 4  #Increment the index of the array by 4 to  point to the next index.
           addi $t0, $t0, 1  #Increment $t0 by 1 to keep track of how many elements entered.

	   j while 	     #If we get here, we need to jump back to the while loop and re-do the same operation.


printFistOutPut:
     
        printString(FirstOutput)  #Print out a message to indicate that an unsorted is being printed below.
        printString(RightSq)    #Pring the right closing square bracket.
        
        mul     $t0, $zero, 0  #Reinitialize the register $t0 to 0
	li      $v0, 0         #Reinitialize the register $v0 to 0
        la      $t4, myArray   #Load the address of myArray into $t4
        
loop1:

    
    lw      $t2, 0($t4)   #Load the number at the specified index into $t2
    addi    $t4, $t4, 4   #Increment the index by 4

    printInt($t2)        #Print the element.
    
    
    addi    $t0, $t0, 1   #Increment the $t0 by 1 to keep track of number of elements printed.
    
     beq     $t1, $t0, printSecondOutput #If the number of elements printed is equal to
     					 #the number of elements stored in the array, then go the next step.
     printString(Comma)	         #Otherwise print a comma and continues to loop.
     
     bne $t1, $t0, loop1   #Go back to the loop and repead the operation.

printSecondOutput:
	printString(LeftSq)	  #Print the left closing square bracket.
	printString(BackSpace) #Print a in between both array when printing them.
        printString(SecondOutput) #Print out a message to indicate that an unsorted is being printed below.
        printString(RightSq)	  #Print the left closing square bracket.
        
        
        move $t6, $t1      #Move the content of $t1 into $t6
        add $t2, $0, $0    #Reinitialize the register $t2 to 0
        add $t3, $0, $0    #Reinitialize the register $t3 to 0
        add $t4, $0, $0    #Reinitialize the register $t4 to 0
        add $t5, $0, $0    #Reinitialize the register $t5 to 0
        add $t0, $0, $0    #Reinitialize the register $t0 to 0
        mul $t4, $t6, 4    #Store the product of $t6 times 4 into $t4
        
    la  $t0, myArray      # load the base address of myArray into register $t0
    
    add $t0, $t0, $t4   
                              
outterLoop:             # the outer loop to iterate throug the array with index i
    add $t1, $0, $0     # checks if the whole array has been sorted.
    la  $a0, myArray      # Set $a0 to the base address of myArray
    
innerLoop:                  # This loop is used within the array to check if we need to swap or not.
    lw  $t2, 0($a0)         # Load $t2 witht the element at the specified index
    lw  $t3, 4($a0)         # Load $t3 witht the element at the specified index

    slt $t5, $t3,  $t2      # If $t3 is less than $t2, then set $t5 to 1, otherwise 0
    beq    $t5, $0, pickALoop   # if $t5 is equal to 1, then swap proceed to swap, otherwise jump to 
    				#the designated label.

    sw  $t2, 4($a0)         # store the greater number the next greater index.
    sw  $t3, 0($a0)         # store the small number in the previous lower index.
    
    add $t1, $0, 1          # If we need to swap again, then we increment $t1 by 1.
    
pickALoop:
    addi $a0, $a0, 4            # Increment the index by 4 to point to the next index
    bne  $a0, $t0, innerLoop    # If $a0 has not reached the end ot fhe array, 
    				#then go back to innerLoop and repeat the process.
    bne  $t1, $0, outterLoop    # If $t1 = 1, then we need to go back to the outterLoop and repead the process     
    
printingSecondOuput:

        mul     $t0, $zero, 0  #Reinitialize the register $t0 to 0
	li      $v0, 0         #Reinitialize the register $v0 to 0
        la      $t4, myArray   #Load the address of myArray into $t4
        
        addi    $t4, $t4, 4     #For some weird reason, this line helps with printing all
    			#elements of the sorted array, otherwise it does not print 2 of them. 
loop2:
     
    lw      $t2, 0($t4)   #Load the number at the specified index into $t2
    addi    $t4, $t4, 4   #Increment the index by 4
    
    printInt($t2)         #Print the element.

   addi    $t0, $t0, 1       #Increment the $t0 by 1 to keep track of number of elements printed.
    
    beq     $t6, $t0, last   #If the number of elements printed is equal to
     			     #the number of elements stored in the array, then go the next step.
    printString(Comma)      #Otherwise print a comma and continues to loop.
     
    bne $t6, $t0, loop2    #Go back to the loop and repead the operation.

  last:                 #This is only used to print the closing bracket.
  printString(LeftSq)   #Print the left closing square bracket.

   exit:    #If we get here, then stop the program.
  li $v0,    10
  syscall
