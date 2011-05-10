------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: RoadPricing
PURPOSE OF PROJECT: Simulate vehicles on a road network and calculate the charge applied to them.
VERSION or DATE: 10/05/11
HOW TO START THIS PROJECT: Run "void main (String args[])" in the world class.
AUTHORS: Greg Myers
USER INSTRUCTIONS:
(1)Run "void main (String args[])" in the world class.
(2)Input the ammount of milliseconds you want each simulated second to take. Input 0 for the system to run as fast as possible.

SegmentData.txt, Segments.txt and Vehicles.txt are in the data subdirectory "~/data"

The program will show the vehicles moving around and generate a SegmentsData.txt as it runs.

The program will only add a cost to the vehicle once it has completed the section.

The program can have unlimited nodes, just keep adding them to Segments.txt

The program has a framerate of 25fps no matter how many ingame seconds have passed inbetween the last 2 frames as framerate and updates are kept seperate.

If you pause the program within the debugger and then continue running it, the program will try to catch up on the missed time by running at 0ms/simulated second.

There is a random error when on startup, the image buffers for double buffering will not initialise properly.
This happens about 1 in 20 times for unknown reasons.