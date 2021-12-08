import React, { useState } from 'react';

import Menu from './Menu/Menu';
import Content from './Content/Content';

import { MenuItemType } from './typing';
import { AirportContext } from './Contexts/AirportContext';

const AirportApp = () => {
    const [selectedMenuItem, setSelectedMenuItem] = useState<MenuItemType>(MenuItemType.AIRPORT_SEARCH);
    const [selectedCountry, setSelectedCountry] = useState<string | undefined>(undefined);
    const [selectedRegion, setSelectedRegion] = useState<string | undefined>(undefined);

    return (
        <AirportContext.Provider
            value={{
                selectedMenuItem,
                setSelectedMenuItem,
                selectedCountry,
                setSelectedCountry,
                selectedRegion,
                setSelectedRegion,
            }}
        >
            <Menu />
            <Content />
        </AirportContext.Provider>
    );
};

export default AirportApp;
