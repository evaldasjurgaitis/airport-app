import React, { useState } from 'react';

import Menu from './Menu/Menu';
import Content from './Content/Content';

import { MenuItemType } from './typing';

const AirportApp = () => {
    const [selectedMenuItem, setSelectedMenuItem] = useState<MenuItemType>(MenuItemType.FLIGHT_SEARCH);
    return (
        <div>
            <Menu selectedMenuItem={selectedMenuItem} setSelectedMenuItem={setSelectedMenuItem} />
            <Content selectedMenuItem={selectedMenuItem} />
        </div>
    );
};

export default AirportApp;
