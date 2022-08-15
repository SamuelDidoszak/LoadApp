# LoadApp
Android application listing available jobs in the transport segment

The app was written in conjuction with a heroku server which provides api with several endpoints used by this app.

<img src="https://user-images.githubusercontent.com/70522994/184716936-3e2d0b79-1d4e-4822-8333-89d35575865d.gif" width="17%" align="left"></img>
## Login / Register
Login / Register screen downloads supported languages, qualifications and their pictures from the server. Parsing login/register data and checking its correctness is done in this app.

## Main screen
<img src="https://user-images.githubusercontent.com/70522994/184717705-46b01d07-dd21-4d44-9326-9c44ff0dcbda.gif" width="17%" align="right"></img>
Main screen shows the list of available offers. User can click on them to see the details and send an application. Those offers are fetched through an API.

Offers are also displayed on the map, which is easily available after dragging the offer list. It shows the location of offers which open the particular offer when clicked.
There is also screen for Companies to make new offers
