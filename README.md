# AP2-FlightSimulator-AndroidApp-Joystick
Second year second semester- "Advanced programming 2" course- Android app- joystick

<p float="left">
  <img src="https://user-images.githubusercontent.com/45766976/115511339-685bd480-a289-11eb-8638-d1a011108a6d.jpg" width="150">
  <img src="https://user-images.githubusercontent.com/45766976/115511342-698d0180-a289-11eb-9e3e-70b7b72b76c8.jpg" width="150">
  <img src="https://user-images.githubusercontent.com/45766976/115511328-65f97a80-a289-11eb-9d88-a7dd315bd04d.jpg" width="150">
  <img src="https://user-images.githubusercontent.com/45766976/115225227-83f29e00-a116-11eb-826a-3b7be488072f.png" width="440">
</p>


in order to get it to work:
1. Open FlightGear on your PC, and write this in FlightGear --> Settings --> Additional settings:

--generic=socket,out,10,127.0.0.1,5402,tcp,generic_small
 
--telnet=socket,in,10,127.0.0.1,5400,tcp

2. Find your PC's IP(cmd --> ipconfig --> IPv4)

3. In your Android device, Connect to the same network as your PC, Open the app, and enter the IP from the previous section, with port 5400.

Enjoy! :)
