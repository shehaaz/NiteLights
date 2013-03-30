NiteLights
==========

Android App to find events in the city of Montreal


Dev. Notes:

put num. commits instead of address in venue list

add ranking tab and sort the venue according to attendance 

*Use Polymorphism to make specific Wire Stories*

AttendanceStory extends WireFactory{
toString(){sysout(Name + "is Committed to" + Venue );}
}

FriendshipStory extends WireFactory {
toString(){sysout(Name "is Friends with" Name);}
}

This does not screw with anything because they will still be WireFactory objects in the Data Structure and the Adapter will not complain!!! 
