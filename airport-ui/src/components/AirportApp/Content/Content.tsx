import React from 'react';

import AirportSearchContent from './AirportSearchContent/AirportSearchContent';
import AdminPanelContent from './AdminPanelContent/AdminPanelContent';

import { MenuItemType } from '../typing';

interface Props {
    selectedMenuItem: MenuItemType;
}

const Content = ({ selectedMenuItem }: Props) => {
    switch (selectedMenuItem) {
        case MenuItemType.AIRPORT_SEARCH:
            return <AirportSearchContent />;
        case MenuItemType.ADMIN_PANEL:
            return <AdminPanelContent />;
        default:
            return <></>;
    }
};

export default Content;
