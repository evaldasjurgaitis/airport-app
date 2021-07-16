import React, { useState } from 'react';

import AirportSearchForm from './AirportSearchForm/AirportSearchForm';
import AirportTable from './AirportTable/AirportTable';

import './AirportSearchContent.scss';

const AirportSearchContent = () => {
    const [selectedCountry, setSelectedCountry] = useState<string | undefined>(undefined);
    const [selectedRegion, setSelectedRegion] = useState<string | undefined>(undefined);

    return (
        <>
            <div className="airport-search-content">
                <h1>Airport search panel</h1>
                <AirportSearchForm
                    selectedCountry={selectedCountry}
                    selectedRegion={selectedRegion}
                    setSelectedCountry={setSelectedCountry}
                    setSelectedRegion={setSelectedRegion}
                />
            </div>
            <AirportTable selectedCountry={selectedCountry} selectedRegion={selectedRegion} />
        </>
    );
};

export default AirportSearchContent;
