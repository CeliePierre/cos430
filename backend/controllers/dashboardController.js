const staffDashboard = (req, res) => {
  if (req.user.role !== "staff") {
    return res.status(403).json({ message: "Access denied: Staff only" });
  }

  res.json({ message: `Hello Staff ${req.user.id}` });
};

const visitorDashboard = (req, res) => {
  if (req.user.role !== "visitor") {
    return res.status(403).json({ message: "Access denied: Visitors only" });
  }

  res.json({ message: `Hello Visitor ${req.user.id}` });
};

module.exports = {
  staffDashboard,
  visitorDashboard,
};
