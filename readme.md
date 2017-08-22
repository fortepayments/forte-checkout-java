This sample code will demonstrate how to create a simple Pay Now button for Checkout using Java. 

Change the Secure Transaction Key and API Login ID located in the the AuthenticationHelper.java file.
The pay.jsp file will generate a button that will launch Checkout.

After testing is complete. 
Change the Secure Transaction Key and API Login ID in AuthenticationHelper.java to their production values.
Change this line <script type="text/javascript" src="https://sandbox.forte.net/checkout/v1/js"> in pay.jsp to point to the production enviroment. <script type="text/javascript" src="https://checkout.forte.net/v1/js">