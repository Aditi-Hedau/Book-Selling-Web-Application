import { Fragment, useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { Dialog, Popover, Tab, Transition } from "@headlessui/react";
import { Bars3Icon, MagnifyingGlassIcon, ShoppingBagIcon, XMarkIcon } from "@heroicons/react/24/outline";
import { Avatar } from "@mui/material";
import { deepPurple } from "@mui/material/colors";
import { useDispatch, useSelector } from "react-redux";
import { getUser, logout } from "../../../Redux/Auth/Action";
import { getCart } from "../../../Redux/Customers/Cart/Action";
import { navigation } from "../../../config/navigationMenu";

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Navigation() {
  const [open, setOpen] = useState(false);
  const [anchorEl, setAnchorEl] = useState(null);
  const [showMenu, setShowMenu] = useState(false);
  const [openAuthModal, setOpenAuthModal] = useState(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { auth } = useSelector((store) => store);
  const jwt = localStorage.getItem("jwt");
  const location = useLocation();

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(jwt));
      dispatch(getCart(jwt));
    }
  }, [jwt]);

  useEffect(() => {
    if (auth.user) {
      handleClose();
    }
    if (location.pathname === "/login" || location.pathname === "/register") {
      navigate(-1);
    }
  }, [auth.user]);

  const handleUserClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleCloseUserMenu = () => {
    setAnchorEl(null);
  };

  const handleOpen = () => {
    setOpenAuthModal(true);
  };
  const handleClose = () => {
    setOpenAuthModal(false);
  };

  const handleLogout = () => {
    handleCloseUserMenu();
    dispatch(logout());
  };

  const handleMyOrderClick = () => {
    handleCloseUserMenu();
    auth.user?.role === "ROLE_ADMIN"
      ? navigate("/admin")
      : navigate("/account/order");
  };

  return (
    <Fragment>
      {/* Navigation bar */}
      <nav className="bg-blue-700 text-white border-b border-gray-200 lg:px-20 px-2">
        <div className="mx-auto px-4">
          <div className="flex justify-between items-center">
            {/* Logo */}
            <Link to="/" className="flex items-center py-6">
              <img
                src="https://res.cloudinary.com/ddkso1wxi/image/upload/v1675919455/Logo/Copy_of_Zosh_Academy_nblljp.png"
                alt="Shopwithzosh"
                className="h-8 w-8 mr-2"
              />
              <span className="font-bold text-white text-lg">
                Shop With Zosh
              </span>
            </Link>

            {/* Navigation menu */}
            <ul className="hidden md:flex items-center space-x-4">
              <li>
                <Link to="/booklist" className="font-medium text-white hover:text-black">
                  Booklist
                </Link>
              </li>
              <li>
                <Link to="/contactus" className="font-medium text-white hover:text-black">
                  Contact Us
                </Link>
              </li>
              <li>
                <Link to="/aboutus" className="font-medium text-white hover:text-black">
                  About Us
                </Link>
              </li>
            </ul>

            {/* Search bar */}
            <form className="hidden md:block flex-grow max-w-sm">
              <div className="relative w-full">
                <input
                  type="search"
                  className="block w-full border border-gray-300 rounded-md py-2 pl-10 pr-3 leading-5 placeholder-gray-500 focus:outline-none focus:placeholder-gray-400 focus:border-blue-500 focus:ring-blue-500 focus:text-gray-900 sm:text-sm"
                  placeholder="Search"
                />
                <div className="absolute inset-y-0 left-0 flex items-center justify-center pl-3">
                  <MagnifyingGlassIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
                </div>
              </div>
            </form>

            {/* User and Cart */}
            <div className="flex items-center space-x-4">
              {auth.user ? (
                <Avatar
                  className="text-white cursor-pointer"
                  onClick={handleUserClick}
                  aria-controls={anchorEl ? "user-menu" : undefined}
                  aria-haspopup="true"
                  aria-expanded={anchorEl ? "true" : undefined}
                  sx={{
                    bgcolor: deepPurple[500],
                    color: "white",
                  }}
                >
                  {auth.user.firstName[0].toUpperCase()}
                </Avatar>
              ) : (
                <button onClick={handleOpen} className="text-white">
                  Sign In
                </button>
              )}
              <Link to="/cart" className="text-white flex items-center">
                <ShoppingBagIcon className="h-6 w-6" aria-hidden="true" />
              </Link>
            </div>

            {/* Mobile navigation menu */}
            <div className="md:hidden flex items-center">
              <button onClick={() => setShowMenu(!showMenu)} className="text-white p-2">
                <Bars3Icon className="h-6 w-6" aria-hidden="true" />
              </button>
            </div>
          </div>
        </div>
      </nav>

      {/* Mobile menu */}
      {showMenu && (
        <div className="md:hidden bg-white">
          <div className="flex px-4 py-2">
            <button
              type="button"
              className="inline-flex items-center justify-center rounded-md p-2 text-gray-400"
              onClick={() => setShowMenu(false)}
            >
              <span className="sr-only">Close menu</span>
              <XMarkIcon className="h-6 w-6" aria-hidden="true" />
            </button>
          </div>
          <ul className="flex flex-col py-4 space-y-2 px-5">
            <li>
              <Link to="/booklist" className="font-medium text-gray-900 hover:text-gray-700">
                Booklist
              </Link>
            </li>
            <li>
              <Link to="/contactus" className="font-medium text-gray-900 hover:text-gray-700">
                Contact Us
              </Link>
            </li>
            <li>
              <Link to="/aboutus" className="font-medium text-gray-900 hover:text-gray-700">
                About Us
              </Link>
            </li>
          </ul>
        </div>
      )}

      {/* User menu */}
      {anchorEl && (
        <div
          id="user-menu"
          className="absolute right-0 mt-2 w-48 bg-white border border-gray-200 shadow-lg"
        >
          <div className="py-1">
            <button onClick={handleMyOrderClick} className="block px-4 py-2 text-gray-700 hover:bg-gray-100 w-full text-left">
              My Orders
            </button>
            <button onClick={handleLogout} className="block px-4 py-2 text-gray-700 hover:bg-gray-100 w-full text-left">
              Logout
            </button>
          </div>
        </div>
      )}

      {/* Authentication Modal */}
      {openAuthModal && (
        <AuthModal open={openAuthModal} handleClose={handleClose} />
      )}
    </Fragment>
  );
}
