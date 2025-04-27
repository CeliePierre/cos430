import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { checkAuth } from "../services/api"; 
import Calendar from "react-calendar"; 
import "react-calendar/dist/Calendar.css"; 

export default function VolunteerDashboard() {
  const [volunteerOpportunities, setVolunteerOpportunities] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState("");
  const [mockSchedule, setMockSchedule] = useState([]);
  const [selectedDate, setSelectedDate] = useState(new Date()); 
  const [highlightedDates, setHighlightedDates] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchOpportunities = async () => {
      const opportunities = [
        {
          opportunityID: "1",
          title: "Dog Walking Event",
          description: "Walk dogs at the shelter and provide them with some exercise.",
          location: "Shelter Park",
          date: new Date("2025-05-15T10:00:00Z"),
          time: "10:00 AM",
          volunteersNeeded: 5,
        },
        {
          opportunityID: "2",
          title: "Community Outreach",
          description: "Help organize a community outreach event to raise awareness about pet adoption.",
          location: "Community Center",
          date: new Date("2025-06-01T14:00:00Z"),
          time: "2:00 PM",
          volunteersNeeded: 10,
        },
        {
          opportunityID: "3",
          title: "Animal Care Volunteer",
          description: "Assist with feeding and caring for animals in the shelter.",
          location: "Animal Shelter",
          date: new Date("2025-04-30T09:00:00Z"),
          time: "9:00 AM",
          volunteersNeeded: 3,
        },
      ];
      setVolunteerOpportunities(opportunities);

      const datesToHighlight = opportunities.map((opportunity) =>
        new Date(opportunity.date).toLocaleDateString()
      );
      setHighlightedDates(datesToHighlight);
    };

    const fetchSchedule = () => {
      const schedule = [
        {
          day: "Monday",
          tasks: [
            { time: "9:00 AM", task: "Feed animals in shelter" },
            { time: "1:00 PM", task: "Clean animal enclosures" },
          ],
        },
        {
          day: "Tuesday",
          tasks: [
            { time: "10:00 AM", task: "Dog walking event" },
            { time: "3:00 PM", task: "Volunteer orientation" },
          ],
        },
        {
          day: "Wednesday",
          tasks: [
            { time: "9:00 AM", task: "Assist with animal care" },
            { time: "2:00 PM", task: "Fundraising event preparation" },
          ],
        },
        {
          day: "Thursday",
          tasks: [
            { time: "10:00 AM", task: "Community outreach preparation" },
            { time: "1:00 PM", task: "Help at pet adoption fair" },
          ],
        },
        {
          day: "Friday",
          tasks: [
            { time: "9:00 AM", task: "Shelter clean-up" },
            { time: "4:00 PM", task: "Assisting in adoption process" },
          ],
        },
      ];
      setMockSchedule(schedule);
    };

    fetchOpportunities();
    fetchSchedule();

    const verifyAuth = async () => {
      try {
        const user = await checkAuth(); 
        if (user && user.role === "Volunteer") {
          setIsLoggedIn(true);
          setUserRole(user.role);
        } else {
          setIsLoggedIn(false);
        }
      } catch (err) {
        console.error("Error fetching user info:", err);
        setIsLoggedIn(false);
      }
    };

    verifyAuth();
  }, []);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  if (!isLoggedIn || userRole !== "Volunteer") {
    return (
      <div className="page-wrapper">
        <h1>Volunteer Dashboard</h1>
        <p>You must be logged in as a Volunteer to access this page.</p>
        <button onClick={() => navigate("/login")}>Login</button>
        <button onClick={() => navigate("/signup")}>Sign Up</button>
      </div>
    );
  }

  return (
    <div className="page-wrapper">
      <h1>Volunteer Dashboard</h1>

      <div className="calendar-container">
        <Calendar
          onChange={handleDateChange}
          value={selectedDate}
          tileClassName={({ date, view }) => {
            if (highlightedDates.includes(date.toLocaleDateString())) {
              return "highlighted";
            }
            return "";
          }}
        />
      </div>

      <div className="volunteer-opportunities">
        <h2>Volunteer Opportunities</h2>
        {volunteerOpportunities.length > 0 ? (
          <div className="opportunity-gallery">
            {volunteerOpportunities.map((opportunity) => (
              <div key={opportunity.opportunityID} className="opportunity-card">
                <h3>{opportunity.title}</h3>
                <p><strong>Description:</strong> {opportunity.description}</p>
                <p><strong>Location:</strong> {opportunity.location}</p>
                <p><strong>Date:</strong> {new Date(opportunity.date).toLocaleDateString()}</p>
                <p><strong>Time:</strong> {opportunity.time}</p>
                <p><strong>Volunteers Needed:</strong> {opportunity.volunteersNeeded}</p>
              </div>
            ))}
          </div>
        ) : (
          <p>No volunteer opportunities available at the moment.</p>
        )}
      </div>

      <div className="volunteer-schedule">
        <h2>Volunteer Schedule</h2>
        <div className="schedule-gallery">
          {mockSchedule.map((daySchedule) => (
            <div key={daySchedule.day} className="schedule-card">
              <h3>{daySchedule.day}</h3>
              {daySchedule.tasks.map((task, index) => (
                <div key={index} className="task">
                  <p><strong>{task.time}:</strong> {task.task}</p>
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
