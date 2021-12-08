import React, { useContext } from 'react';

import AirportSearchContent from './AirportSearchContent/AirportSearchContent';
import AdminPanelContent from './AdminPanelContent/AdminPanelContent';

import { MenuItemType } from '../typing';
import { AirportContext } from '../Contexts/AirportContext';

const Content = () => {
    const { selectedMenuItem } = useContext(AirportContext);

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
