import {
  createBrowserRouter,
  RouterProvider,
} from 'react-router-dom'
import './App.css'
import HomePage from './pages/HomePage'
import AdoptionApplication from './pages/AdoptionApplication'
import BrowseAnimals from './pages/BrowseAnimals'
import Layout from './Layout'
import NotFound from './pages/NotFoundPage'
import AnimalProfile from './pages/AnimalProfile'

const routes = [{
    path: '/',
    element: <Layout />,
    errorElement: <NotFound />,
    children: [{
      path: '/',
      element: <HomePage />
    }, {
      path: '/adopt',
      element: <AdoptionApplication />
    }, {
      path: '/browse',
      element: <BrowseAnimals />
    }, {
      path: '/animalProfile',
      element: <AnimalProfile />
    }]
}]

const router = createBrowserRouter(routes);

function App() {
  return (
    <>
    <RouterProvider router={router} />
    </>
  );
}

export default App