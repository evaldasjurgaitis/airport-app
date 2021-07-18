import React, { useCallback, useEffect, useState } from 'react';
import axios from 'axios';

import Modal from '../../../../shared/Modal/Modal';

import { AirportDetail } from '../../../typing';

import './AirportInfoModal.scss';

interface Props {
    airportId: number | null;
    visible: boolean;
    onClose: () => void;
}

const AirportInfoModal = ({ airportId, visible, onClose }: Props) => {
    const [airport, setAirport] = useState<AirportDetail>();

    const fetchAirportData = useCallback(async () => {
        const result = await axios(`http://localhost:8081/api/airports/${airportId}`);
        setAirport(result.data);
    }, [airportId]);

    useEffect(() => {
        if (airportId) {
            fetchAirportData();
        }
    }, [airportId, fetchAirportData]);

    if (!airport) {
        return null;
    }

    return (
        <Modal visible={visible} onClose={onClose}>
            <div className="airport-info-modal__content">
                <h2>Airport ({airport.name}) info: </h2>
                <ul>
                    <li>
                        ID: <strong>{airport.id}</strong>
                    </li>
                    <li>
                        Type: <strong>{airport.type}</strong>
                    </li>
                    <li>
                        Name: <strong>{airport.name}</strong>
                    </li>
                    <li>
                        Longitude: <strong>{airport.longitude ? airport.longitude : 'N/A'}</strong>
                    </li>
                    <li>
                        Latitude: <strong>{airport.latitude ? airport.latitude : 'N/A'}</strong>
                    </li>
                    <li>
                        Altitude: <strong>{airport.altitude ? airport.altitude : 'N/A'}</strong>
                    </li>
                    <li>
                        Continent: <strong>{airport.continent ? airport.continent : 'N/A'}</strong>
                    </li>
                    <li>
                        Country: <strong>{airport.isoCountry ? airport.isoCountry : 'N/A'}</strong>
                    </li>
                    <li>
                        Region: <strong>{airport.isoRegion ? airport.isoRegion : 'N/A'}</strong>
                    </li>
                    <li>
                        Municipality: <strong>{airport.municipality ? airport.municipality : 'N/A'}</strong>
                    </li>
                    <li>
                        Scheduled service:
                        <strong>{airport.sheduledService ? airport.sheduledService : 'FALSE'}</strong>
                    </li>
                    <li>
                        Gps code: <strong>{airport.gpsCode ? airport.gpsCode : 'N/A'}</strong>
                    </li>
                    <li>
                        Iata code: <strong>{airport.iataCode ? airport.iataCode : 'N/A'}</strong>
                    </li>
                    <li>
                        Local code: <strong>{airport.localCode ? airport.localCode : 'N/A'}</strong>
                    </li>
                    <li>
                        Available providers:
                        <strong>
                            {airport.availableProviders.map((provider) => {
                                return (
                                    <span key={provider.name}>
                                        {provider.name.toUpperCase()} {provider.price} {provider.currency}
                                    </span>
                                );
                            })}
                        </strong>
                    </li>
                </ul>
            </div>
        </Modal>
    );
};

export default AirportInfoModal;
