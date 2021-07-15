import React, { useState } from 'react';
import axios from 'axios';

import { Button, Upload } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

import Loader from '../../../../shared/Loader/Loader';
import Row from '../../../../shared/Row/Row';
import Col from '../../../../shared/Col/Col';

import { JobStatus } from '../../../typing';

const AirportDataImport = () => {
    const [showAirportDataLoader, setShowAirportDataLoader] = useState(false);
    const [showAirportDataImportBtn, setShowAirportDataImportBtn] = useState(false);
    const [airportDataFilePath, setAirportDataFilePath] = useState('');
    const [showAirportDataImportStatusFailure, setShowAirportDataImportStatusFailure] = useState(false);
    const [showAirportDataImportStatusSuccess, setShowAirportDataImportStatusSuccess] = useState(false);

    const fileForm = (options: any) => {
        const { file } = options;
        const data = new FormData();
        data.append('file', file);
        return data;
    };

    const uploadAirportData = async (options: any) => {
        setShowAirportDataLoader(true);
        setShowAirportDataImportBtn(false);
        setShowAirportDataImportStatusFailure(false);
        setShowAirportDataImportStatusSuccess(false);
        await axios
            .post(`http://localhost:8082/api/files`, fileForm(options))
            .then((response) => {
                setShowAirportDataLoader(false);
                setShowAirportDataImportBtn(true);
                setAirportDataFilePath(response.data.path);
            })
            .catch(() => {
                setShowAirportDataLoader(false);
                setShowAirportDataImportStatusFailure(true);
            });
    };

    const pollingImportStatus = (jobId: number) => {
        axios
            .get(`http://localhost:8082/api/jobs/${jobId}/status`)
            .then((res) => {
                if (
                    (res.status === 200 && res.data.status === JobStatus.STARTED) ||
                    res.data.status === JobStatus.STARTING
                ) {
                    setTimeout(() => pollingImportStatus(jobId), 5000);
                } else {
                    if (res.data.status === JobStatus.COMPLETED) {
                        setShowAirportDataLoader(false);
                        setShowAirportDataImportStatusSuccess(true);
                    }
                    if (
                        !res ||
                        res.data.status === JobStatus.FAILED ||
                        res.data.status === JobStatus.STOPPING ||
                        res.data.status === JobStatus.STOPPED ||
                        res.data.status === JobStatus.ABANDONED
                    ) {
                        setShowAirportDataLoader(false);
                        setShowAirportDataImportStatusFailure(true);
                    }
                }
            })
            .catch(function () {
                setShowAirportDataLoader(false);
                setShowAirportDataImportStatusFailure(true);
            });
    };

    const executeImportData = async () => {
        setShowAirportDataImportBtn(false);
        setShowAirportDataLoader(true);
        axios
            .post('http://localhost:8082/api/jobs/AIRPORT_DATA_IMPORT/execute', {
                filePath: airportDataFilePath,
            })
            .then(async () => {
                setTimeout(() => {
                    axios
                        .get('http://localhost:8082/api/jobs/AIRPORT_DATA_IMPORT')
                        .then(async (response) => {
                            pollingImportStatus(response.data.id);
                        })
                        .catch(function () {
                            setShowAirportDataLoader(false);
                            setShowAirportDataImportStatusFailure(true);
                        });
                }, 4500);
            })
            .catch(function () {
                setShowAirportDataLoader(false);
                setShowAirportDataImportStatusFailure(true);
            });
    };

    return (
        <Row align="middle" className="admin-panel--row">
            <Col span={3} xs={10} sm={3} md={5} xl={3}>
                <div className="align-left">
                    <Upload accept=".csv" customRequest={uploadAirportData} showUploadList={false} maxCount={1}>
                        <Button disabled={showAirportDataLoader} icon={<UploadOutlined />}>
                            Airports upload
                        </Button>
                    </Upload>
                </div>
            </Col>
            <Col span={3} xs={9} sm={3} md={4} xl={2}>
                <div className="align-center">
                    {showAirportDataLoader && <Loader />}
                    {showAirportDataImportBtn && <Button onClick={executeImportData}>Import data</Button>}
                    {showAirportDataImportStatusSuccess && (
                        <span className="admin-panel--import-data__success">Success</span>
                    )}
                    {showAirportDataImportStatusFailure && (
                        <span className="admin-panel--import-data__failure">Failure</span>
                    )}
                </div>
            </Col>
        </Row>
    );
};

export default AirportDataImport;