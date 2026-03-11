###########################################################
#  joel kalala
#   Playing with :
#     Simple input output of string and intergers
#     stopping execution
###########################################################
#compute (9 + 16) - (3 + 22)

    li      $s0, 9          # $s0 = 9
    li      $s1, 16         # $s1 = 16
    add     $s0, $s0, $s1   # $s0 = $s0 + $s1, so now $s0 = 25 which is the result of 9 + 16

    li      $s1, 3          # $s1 = 3
    addi    $s1, $s1, 22    # $s1 = $s1 + 22, so now $s1 = 25 which is the result of 3 + 22

    sub     $s0, $s0, $s1   # $s0 = $s0 - $s1, compute (9 + 16) - (3 + 22)