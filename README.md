# Pond Sizes

The purpose of this code is to detect adjoining 'ponds' represented as zeros on a matrix.

A pond is represented in the matrix as a *zero* and dry land is represented as a *one*.
Any adjoining pond is regarded as being part of the same pond.

**Example 1**

>101<br>
>111<br><br>
>This matrix contains one pond of size one.

**Example 2**

>100<br>
>111<br><br>
>This matrix contains one pond of size two.

Ponds are considered to be adjoining whether they are vertically or horizontally adjacent, or diagonally adjacent.

**Example 3**

>100<br>
>110<br><br>
>This matrix contains one pond of size three.

**Example 4**

>100<br>
>010<br><br>
>This matrix contains one pond of size four.

**Example 5**

>101<br>
>011<br>
>110<br><br>
This matrix contains two ponds. Pond 1 has size 2, pond 2 has size 1.

The tests are specified using gherkin format, whereby the pond under test is specified in the 'given' statement.
The resulting pond(s) that are expected as specified in the 'then' statement.