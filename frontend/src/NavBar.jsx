import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function NavBar() {
  return (
    <nav>
      <ul>
      <li>
          <Link to='/'>Home</Link>
        </li>
        <li>
          <Link to='/browse'>Browse</Link>
        </li>
        <li>
          <Link to='/adopt'>Adopt</Link>
        </li>
      </ul>
    </nav>
  )
}