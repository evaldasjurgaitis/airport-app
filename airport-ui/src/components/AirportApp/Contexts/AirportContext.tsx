import React, { createContext } from 'react';
import { MenuItemType } from '../typing';

export type AirportContent = {
    selectedMenuItem: MenuItemType;
    setSelectedMenuItem: (item: MenuItemType) => void;
    selectedCountry: string | undefined;
    setSelectedCountry: (item: string | undefined) => void;
    selectedRegion: string | undefined;
    setSelectedRegion: (item: string | undefined) => void;
};

export const AirportContext = createContext<AirportContent>({
    selectedMenuItem: MenuItemType.AIRPORT_SEARCH,
    setSelectedMenuItem: () => {},
    selectedCountry: undefined,
    setSelectedCountry: () => {},
    selectedRegion: undefined,
    setSelectedRegion: () => {},
});
