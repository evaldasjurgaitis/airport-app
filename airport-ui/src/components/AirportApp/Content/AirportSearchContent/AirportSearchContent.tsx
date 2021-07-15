import React from 'react';

import AirportSearchForm from './AirportSearchForm/AirportSearchForm';

import './AirportSearchContent.scss';

const AirportSearchContent = () => {
    return (
        <div className="airport-search-content">
            <h1>Airport search panel</h1>
            <AirportSearchForm />
        </div>
    );
};

export default AirportSearchContent;
