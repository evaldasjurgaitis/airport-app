import React, { useState } from 'react';

import AirportSearchForm from './AirportSearchForm/AirportSearchForm';
import AirportTable from './AirportTable/AirportTable';

const AirportSearchContent = () => {
    const [selectedCountry, setSelectedCountry] = useState<string | undefined>(undefined);
    const [selectedRegion, setSelectedRegion] = useState<string | undefined>(undefined);

    return (
        <>
            <AirportSearchForm
                selectedCountry={selectedCountry}
                selectedRegion={selectedRegion}
                setSelectedCountry={setSelectedCountry}
                setSelectedRegion={setSelectedRegion}
            />
            <AirportTable selectedCountry={selectedCountry} selectedRegion={selectedRegion} />
        </>
    );
};

export default AirportSearchContent;
