import React from 'react';

import { MenuItemType } from '../typing';

import './Content.scss';

interface Props {
    selectedMenuItem: MenuItemType;
}

const Content = ({ selectedMenuItem }: Props) => {
    switch (selectedMenuItem) {
        case MenuItemType.FLIGHT_SEARCH:
            return <div className="content">Flight Search</div>;
        case MenuItemType.ADMIN_PANEL:
            return <div className="content">Admin Panel</div>;
        default:
            return <div className="content">No content found</div>;
    }
};

export default Content;
