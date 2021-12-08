import React, { useContext } from 'react';

import { MenuItemType } from '../typing';

import './Menu.scss';
import { AirportContext } from '../Contexts/AirportContext';

const Menu = () => {
    const { selectedMenuItem, setSelectedMenuItem } = useContext(AirportContext);
    return (
        <div className="menu">
            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
            <a
                href=""
                className={selectedMenuItem === MenuItemType.AIRPORT_SEARCH ? 'menu__active' : ''}
                onClick={(e) => {
                    e.preventDefault();
                    setSelectedMenuItem(MenuItemType.AIRPORT_SEARCH);
                }}
            >
                Airport Search
            </a>
            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
            <a
                href=""
                className={selectedMenuItem === MenuItemType.ADMIN_PANEL ? 'menu__active' : ''}
                onClick={(e) => {
                    e.preventDefault();
                    setSelectedMenuItem(MenuItemType.ADMIN_PANEL);
                }}
            >
                Admin panel
            </a>
        </div>
    );
};

export default Menu;
