import React from 'react';

import AdminPanelContent from './AdminPanelContent/AdminPanelContent';
import { MenuItemType } from '../typing';

import './Content.scss';
import AirportSearchContent from './AirportSearchContent/AirportSearchContent';

interface Props {
    selectedMenuItem: MenuItemType;
}

const Content = ({ selectedMenuItem }: Props) => {
    switch (selectedMenuItem) {
        case MenuItemType.AIRPORT_SEARCH:
            return (
                <div className="content">
                    <AirportSearchContent />
                </div>
            );
        case MenuItemType.ADMIN_PANEL:
            return (
                <div className="content">
                    <AdminPanelContent />
                </div>
            );
        default:
            return <div className="content">No content found</div>;
    }
};

export default Content;
