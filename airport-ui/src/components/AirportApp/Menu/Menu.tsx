import React, { Dispatch, SetStateAction } from 'react';

import { MenuItemType } from '../typing';

import './Menu.scss';

interface Props {
    selectedMenuItem: MenuItemType;
    setSelectedMenuItem: Dispatch<SetStateAction<MenuItemType>>;
}

const Menu = ({ selectedMenuItem, setSelectedMenuItem }: Props) => {
    return (
        <div className="menu">
            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
            <a
                href=""
                className={selectedMenuItem === MenuItemType.FLIGHT_SEARCH ? 'menu__active' : ''}
                onClick={(e) => {
                    e.preventDefault();
                    setSelectedMenuItem(MenuItemType.FLIGHT_SEARCH);
                }}
            >
                Flight Search
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
